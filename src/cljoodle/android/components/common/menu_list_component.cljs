(ns cljoodle.android.components.common.menu-list-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]))


(defn menu-list-component
  "Component for generating list of items
  * data - items with format [{:on-press :title}]"
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
                                               :on-press (:on-press head)}
                       [rw/text {:style {:color       "white"
                                         :text-align  "center"
                                         :font-weight "bold"}} (:title head)]]]))))))
