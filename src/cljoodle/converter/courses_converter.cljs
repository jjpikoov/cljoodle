(ns cljoodle.converter.courses-converter)

(defn convert-course-to-menu-item-format
  [on-press-function course]
  {:title    (:shortname course)
   :on-press (on-press-function (:id course))})
