(ns cljoodle.http.courses
  (:require [cljoodle.http.util.requests :as requests]
            [cljoodle.http.common :as http-comm])
  (:use [re-frame.core :only [dispatch]]))


(defn get-courses
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
