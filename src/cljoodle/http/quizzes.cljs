(ns cljoodle.http.quizzes
  (:require [cljoodle.http.util.requests :as requests]
            [cljoodle.http.common :as http-comm]))

(defn get-quizzes
  [handler token course-id]
  (let
    [symbols-needed [:id :name]
     get-only-needed-symbols-from-maps #(map (fn [c] (select-keys c symbols-needed)) %)]
    (requests/do-post-request
      #(-> %
           :body
           :quizzes
           get-only-needed-symbols-from-maps
           handler)
      http-comm/rest-server-url
      {:wstoken                token
       (keyword "courseids[]") course-id
       :wsfunction             "mod_quiz_get_quizzes_by_courses"})))

(defn get-quiz-eligibility
  [handler token quiz-id]
  (requests/do-post-request
    #(-> %
         :body
         :canattempt
         handler)
    http-comm/rest-server-url
    {:wstoken    token
     :quizid     quiz-id
     :wsfunction "mod_quiz_get_quiz_access_information"}))

(defn get-quiz-type
  [handler token quiz-id]
  (requests/do-post-request
    #(-> %
         :body
         :questiontypes
         handler)
    http-comm/rest-server-url
    {:wstoken    token
     :quizid     quiz-id
     :wsfunction "mod_quiz_get_quiz_required_qtypes"}))
