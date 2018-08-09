(ns cljoodle.http.moodle.requests
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))
(def url-and-port "http://10.0.3.2:80")
(def base-moodle-url (str url-and-port "/webservice/rest/server.php?moodlewsrestformat=json"))

(defn do-post-request
  ([wstoken wsfunction handler form-params]
   (let [form-params-to-pass (merge {:wstoken    wstoken
                                     :wsfunction wsfunction} form-params)]
     (go (let [response (<! (http/post base-moodle-url {:form-params form-params-to-pass}))]
           (apply handler (list response))))))
  ([handler url form-params]

   (go (let [response (<! (http/post url {:form-params form-params}))]
         (apply handler (list response))))))

(defn print-post-request-response
  "doc-string"
  [wstoken wsfunction form-params]
  (do-post-request wstoken wsfunction prn form-params))
