(ns cljoodle.android.components.menu-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]))

(defn menu-component
  []
  (fn []
    [comm/view {:style {:flex-direction "column"
                        :margin         40
                        :align-items    "center"}}
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     [comm/text "foo"]
     ]))
