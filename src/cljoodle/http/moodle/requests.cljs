(ns cljoodle.http.moodle.requests
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn do-post-request
  "Sends asynchronously post request with rom-params & triggers handler on response"
  ;TODO probably to delete this variant
  ([wstoken wsfunction handler form-params]
   (let [form-params-to-pass (merge {:wstoken wstoken :wsfunction wsfunction} form-params)]
     (go (let [response (<! (http/post base-moodle-url {:form-params form-params-to-pass}))]
           (apply handler (list response))))))
  ([handler url form-params]
   (go (let [response (<! (http/post url {:form-params form-params}))]
         (handler response))
       )))

(defn _print-post-request-response
  ;; TODO Just for debugging, is it used?
  [wstoken wsfunction form-params]
  (do-post-request wstoken wsfunction prn form-params))
