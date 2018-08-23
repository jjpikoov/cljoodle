(ns cljoodle.android.components.events-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.http.events :as evt]
    [cljoodle.android.components.common.styles :as styles]
    [cljoodle.android.components.common.navigator-component :as nav]))


(defn event-list-component
  [data]
  (loop [remaining-data data
         converted-data []]
    (if (empty? remaining-data)
      converted-data
      (let [[head & rest] remaining-data]
        (recur rest
               (into converted-data
                     [[rw/touchable-highlight {:style    {:background-color "#6e00ce"
                                                          :padding          5
                                                          :margin           5}
                                               :on-press (:on-press (fn [] #(prn (:name head))))}
                       [rw/text {:style {:color       "white"
                                         :text-align  "center"
                                         :font-weight "bold"}} (:name head)]]]))))))

(defn events-component
  []
  ; subscribe
  (let [token (subscribe [:get-token])
        course-id (subscribe [:get-active-course-id])
        events (subscribe [:get-events])]
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
            (event-list-component @events))
      ]]))

