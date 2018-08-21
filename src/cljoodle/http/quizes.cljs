(ns cljoodle.http.quizes
  (:require [cljoodle.http.util.requests :as requests]
            [cljoodle.http.common :as http-comm]
            [env.moodle.config :as moodle-config])
  (:use [re-frame.core :only [dispatch]]))


(defn get-quizes
  [handler token course-id]
  (requests/do-post-request
    #(-> %
         :body
         handler)
    http-comm/rest-server-url
    {:wstoken                token
     (keyword "courseids[]") course-id
     :wsfunction             "mod_quiz_get_quizzes_by_courses"}))
