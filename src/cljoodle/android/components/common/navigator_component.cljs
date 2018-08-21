(ns cljoodle.android.components.common.navigator-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]))


(defn navigator-component
  [title]
  (let
    [previous-view (subscribe [:get-previous-view])]
    [rw/view {:style {:flex-direction  "row"
                      :align-items     "center"
                      :justify-content "space-between"
                      :backgroundColor "#0066cc"
                      :height          50}}
     [rw/button {:style   {}
                 :title   "back"
                 :onPress #(dispatch [:set-active-view @previous-view])}]
     [rw/text {:style     {:text-align  "center"
                           :font-weight "bold"
                           :color       "#f0f8ff"}
               :flex-grow 1} title]
     [rw/button {:style   {}
                 :title   "menu"
                 :onPress #(dispatch [:set-active-view "menu-component"])}]]))