(ns cljoodle.android.components.events-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.http.events :as evt]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.converter.events-converter :as ec]
    [cljoodle.android.components.common.navigator-component :as nav]))


(defn _event-list-component
  [data]
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
                            [rw/text {:style {:text-align "center"}} description]]))]]))))))

(defn events-component
  []
  ; subscribe
  (let [token (subscribe [:get-token])
        course-id (subscribe [:get-active-course-id])
        events (subscribe [:get-events])
        ;util functions
        converting-function (partial ec/convert-event-to-menu-item-format
                                     (fn [id] (fn [] (prn id))))
        converted-events (map converting-function @events)]
    ; fetch
    (if (nil? @course-id)
      (dispatch [:set-active-view "courses-component"])
      (if (nil? @events)
        (evt/get-events #(dispatch [:set-events %])
                        @token
                        @course-id)))
    [rw/view (nav/navigator-component "Events")
     [rw/view styles/items-list-container-style
      (into [rw/scroll-view]
            (_event-list-component converted-events))]]))

