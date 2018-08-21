(ns cljoodle.android.components.quizes-component
  (:require
    [re-frame.core :refer [subscribe dispatch dispatch-sync]]
    [cljoodle.android.components.common.react-wrappers :as rw]
    [cljoodle.android.components.common.navigator-component :as nav]
    [cljoodle.android.components.common.menu-list-component :as menu-list]
    [cljoodle.converter.courses-converter :as cc]
    ))

(defn quizes-component
  []
  (let
    [course-id (subscribe [:get-active-course-id])]
    (if (nil? @course-id)
      (dispatch [:set-active-view "courses-component"]))
    [rw/view (nav/navigator-component "Quizes")]))
