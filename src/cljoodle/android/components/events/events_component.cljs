(ns cljoodle.android.components.events.events-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.http.events :as evt]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.converter.events-converter :as ec]
    [cljoodle.android.components.common.navigator-component :as nav]))


(defn- event-list-component
  "Generates list with events based on given data
  * data - output of cljoodle.converter.events-converter/convert-event-to-menu-item-format
  * token - user token for request
  * course-id - id of active course"
  [data token course-id]
  (let [on-remove-func
        (fn [head]
          (fn []
            (do (evt/remove-event (fn []
                                    (dispatch [:set-active-view "events-component"])) token (:id head))
                (evt/get-events #(dispatch [:set-events %]) course-id token))))]
    (loop [remaining-data data
           converted-data []]
      (if (empty? remaining-data)
        converted-data
        (let [[head & rest] remaining-data]
          (recur rest
                 (into converted-data
                       [[rw/view {:style {:background-color "#86f442"
                                          :border-style     "solid"
                                          :border-width     2
                                          :padding          5
                                          :margin           5}}
                         [rw/view {:style {:background-color "#f45e41"}}
                          [rw/text {:style
                                    {:text-align  "center"
                                     :font-weight "bold"}} (:timestart head)]]
                         [rw/view {:style {:background-color "#ebf441"}}
                          [rw/text {:style
                                    {:text-align  "center"
                                     :font-weight "bold"}} (:name head)]]
                         (let [description (:description head)]
                           (if (and (not (nil? description))
                                    (not (= "" description)))
                             [rw/view {:style {:background-color "#41f4dc"}}
                              [rw/text {:style {:text-align "center"}} description]]))

                         [rw/touchable-highlight {:style    {:background-color "#999"
                                                             :border-radius    5}
                                                  :on-press (on-remove-func head)}
                          [rw/text {:style {:color         "white"
                                            :text-align    "center"
                                            :margin-top    3
                                            :margin-bottom 3
                                            :font-weight   "bold"}} "REMOVE"]]]])))))))

(defn- add-button-component
  "Component generates button, after tapping it
  :set-active-view \"events-add-component\" is triggered."
  []
  (dispatch [:clear-new-events-state])
  [rw/view {:style {:flex-direction  "row"
                    :flex-wrap       "nowrap"
                    :justify-content "center"
                    :align-items     "flex-start"
                    :align-content   "stretch"}}
   [rw/touchable-highlight {:style    {:background-color "#a50e9b"
                                       :margin-bottom    5}
                            :on-press #(dispatch [:set-active-view "events-add-component"])}
    [rw/text {:style {:color       "white"
                      :text-align  "center"
                      :padding     15
                      :font-weight "bold"}} "+"]]])

(defn events-component
  []
  ; subscribe
  (let [token (subscribe [:get-token])
        course-id (subscribe [:get-active-course-id])
        events (subscribe [:get-events])
        ;util functions
        converting-function ec/convert-event-to-menu-item-format
        converted-events (map converting-function @events)]
    ; fetch
    (if (nil? @course-id)
      (dispatch [:set-active-view "courses-component"])
      (evt/get-events #(dispatch [:set-events %])
                      @token
                      @course-id))
    [rw/view (nav/navigator-component "Events")
     [rw/view styles/item-list-container-style
      (add-button-component)
      (into [rw/scroll-view]
            (event-list-component converted-events @token @course-id))
      [rw/text {:script
                {:margin-top    2000
                 :text-align    "center"
                 :margin-bottom 200}} "."]
      [rw/text {:script
                {:margin-top    200
                 :text-align    "center"
                 :margin-bottom 200}} "."]]]))

