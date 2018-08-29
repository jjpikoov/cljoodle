;;; File declares converting functions for quizzes
(ns cljoodle.converter.quizzes-converter)

(defn convert-quiz-to-menu-item-format
  "Converts quiz"
  [on-press-function quiz]
  {:title    (:name quiz)
   :id       (:id quiz)
   :on-press (on-press-function (:id quiz))})
