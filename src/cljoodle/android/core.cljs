(ns cljoodle.android.core
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]
    [cljoodle.events]
    [cljoodle.subs]
    [cljoodle.android.components.common :as comm]
    [cljoodle.android.components.login-component :refer [login-component]]
    [cljoodle.android.components.menu-component :refer [menu-component]]
    [cljoodle.android.components.quizes-component :refer [quizes-component]]
    ))

(defn choose-main-component-to-render
  [component-name]
  (if (= component-name "login-component")
    (login-component)
    (if (= component-name "menu-component")
      (menu-component)
      (if (= component-name "quizes-component")
        (quizes-component)))))

(defn app-root
  []
  (let
    [active-view (subscribe [:get-active-view])]
    (fn []
      [comm/view (choose-main-component-to-render @active-view)])))

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerComponent comm/app-registry "cljoodle" #(r/reactify-component app-root)))
