(ns cljoodle.android.components.common.title-component
  (:require
    [cljoodle.android.components.common.react-wrappers :as rw]))

(defn title-component
  [title]
  [rw/view {:style {:flex-direction  "row"
                    :align-items     "center"
                    :justify-content "center"
                    :backgroundColor "#0066cc"
                    :height          50}}
   [rw/text {:style     {:text-align  "center"
                         :font-weight "bold"
                         :color       "#f0f8ff"}
             :flex-grow 1} title]])
