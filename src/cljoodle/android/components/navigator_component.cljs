(ns cljoodle.android.components.navigator-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common :as comm]))


(defn navigator-component
  []
  [comm/view {:style {:flex-direction  "row"
                      :align-items     "center"
                      :backgroundColor "aliceblue"}}
   [comm/text "foo"]
   [comm/text {:style {:flex-grow 1}} ""]
   [comm/text "foo"]
   ])
