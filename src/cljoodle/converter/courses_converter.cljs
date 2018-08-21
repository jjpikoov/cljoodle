(ns cljoodle.converter.courses-converter)

(defn convert-course-to-menu-component-format
  [course]
  {:title    (:shortname course)
   :on-press #(prn (:shortname course))})
