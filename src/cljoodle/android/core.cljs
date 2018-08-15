(ns cljoodle.android.core
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]
    [cljoodle.events]
    [cljoodle.subs]                                         ; TODO is needed?
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.login-component :refer [login-component]]))




(defn app-root
  []
  (fn []
    (login-component)
    ))

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerComponent comm/app-registry "cljoodle" #(r/reactify-component app-root)))
