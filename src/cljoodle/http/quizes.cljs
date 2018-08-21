(ns cljoodle.http.quizzes
  (:require [cljoodle.http.util.requests :as requests]
            [cljoodle.http.common :as http-comm])
  (:use [re-frame.core :only [dispatch]]))


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
