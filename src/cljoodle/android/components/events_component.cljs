(ns cljoodle.android.components.events-component
  (:require [cljoodle.android.components.common.react-wrappers :as rw]
            [cljoodle.android.components.common.styles :as styles]
            [cljoodle.android.components.common.navigator-component :as nav]))

(defn events-component
  []
  [rw/view (nav/navigator-component "Events")
   [rw/view styles/items-list-container-style]])
