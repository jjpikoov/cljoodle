(ns cljoodle.converter.quizzes-converter)

(defn convert-quiz-to-menu-item-format
  [on-press-function course]
  {:title    (:name course)
   :on-press (on-press-function (:id course))})
