(ns cljoodle.android.components.navigator-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]))


(defn navigator-component
  [title]
  [comm/view {:style {:flex-direction  "row"
                      :align-items     "center"
                      :justify-content "space-between"
                      :backgroundColor "#0066cc"
                      :height          50}}
   [comm/button {:style   {}
                 :title   "back"
                 :onPress #(prn "1")}]
   [comm/text {:style     {:text-align  "center"
                           :font-weight "bold"
                           :color       "#f0f8ff"}
               :flex-grow 1} title]
   [comm/button {:style   {}
                 :title   "menu"
                 :onPress #(prn "2")}]
   ])
