;;; File declares converting functions for courses
(ns cljoodle.converter.courses-converter)

(defn convert-course-to-menu-item-format
  "Converts course to menu item format (title, on-press)"
  [on-press-function course]
  {:title    (:shortname course)
   :on-press (on-press-function (:id course))})
