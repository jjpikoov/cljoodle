;;; File declares http service for courses
(ns cljoodle.http.courses
  (:require [cljoodle.http.util.requests :as requests]
            [cljoodle.http.common :as http-comm]))


(defn get-courses
  "Makes http request and uses handler on response"
  [handler token]
  (let
    [symbols-needed [:id :shortname]
     get-only-needed-symbols-from-maps #(map (fn [c] (select-keys c symbols-needed)) %)]
    (requests/do-post-request
      #(-> %
           :body
           get-only-needed-symbols-from-maps
           handler)
      http-comm/rest-server-url
      {:wstoken    token
       :wsfunction "core_course_get_courses"})))
