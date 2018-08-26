(ns cljoodle.converter.quizzes-converter)

(defn convert-quiz-to-menu-item-format
  [on-press-function quiz]
  {:title    (:name quiz)
   :id       (:id quiz)
   :on-press (on-press-function (:id quiz))})
