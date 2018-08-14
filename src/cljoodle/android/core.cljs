(ns cljoodle.android.core
  (:require
    [reagent.core :as r :refer [atom]]
    [re-frame.core :refer [subscribe dispatch dispatch-sync]])
  (:use
    [cljoodle.android.components.common]
    [cljoodle.android.components.login-component]))

(defn app-root
  []
  (login-component)
  )

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerComponent app-registry "cljoodle" #(r/reactify-component app-root)))
