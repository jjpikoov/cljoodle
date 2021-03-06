;;; File declares main function of app
(ns cljoodle.android.core
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [reagent.core :as r :refer [atom]]
    [cljoodle.events]
    [cljoodle.subs]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.login-component :refer [login-component]]
    [cljoodle.android.components.main-menu-component :refer [main-menu-component]]
    [cljoodle.android.components.quizzes-component :refer [quizzes-component]]
    [cljoodle.android.components.courses-component :refer [courses-component]]
    [cljoodle.android.components.events.events-add-component :refer [events-add-component]]
    [cljoodle.android.components.events.events-component :refer [events-component]]))

(defn- choose-main-component-to-render
  "Function depending on component name (returns) component's Hiccup data"
  [component-name]
  (if (= component-name "login-component")
    (login-component)
    (if (= component-name "menu-component")
      (main-menu-component)
      (if (= component-name "quizzes-component")
        (quizzes-component)
        (if (= component-name "courses-component")
          (courses-component)
          (if (= component-name "events-component")
            (events-component)
            (if (= component-name "events-add-component")
              (events-add-component))))))))

(defn app-root
  "Whole app main component

  Every active-view change triggers change of component"
  []
  (let
    [active-view (subscribe [:get-active-view])]
    (fn []
      [rw/view (choose-main-component-to-render @active-view)])))

(defn init
  "Whole app main function"
  []
  (dispatch-sync [:initialize-db])
  (.registerComponent rw/app-registry "cljoodle" #(r/reactify-component app-root)))
