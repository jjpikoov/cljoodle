(ns cljoodle.android.core
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]
    [cljoodle.events]
    [cljoodle.subs]                                         ; TODO is needed?
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.login-component :refer [login-component]]
    [cljoodle.android.components.menu-component :refer [menu-component]]
    ))




(defn app-root
  []
  ;(let [active-view (subscribe [:get-active-view])]
  (fn []
    (let [active-view (subscribe [:get-active-view])]
      (if (= @active-view "login-component")
        (login-component)
        (if (= @active-view "menu-component")
          (menu-component)))
      )))

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerComponent comm/app-registry "cljoodle" #(r/reactify-component app-root)))
