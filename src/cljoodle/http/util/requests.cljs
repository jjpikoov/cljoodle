;;; File declares helpers for making http requests
(ns cljoodle.http.util.requests
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn- debug-request
  "Allows to show requested url, params & response in React Native Debugger (if goog.DEBUG)"
  [url form-params response]
  (if goog.DEBUG
    (js/console.log (str "%cRequested url: " url ",\n"
                         "%cParams: " form-params ",\n"
                         "%cResponse: " (:body response))
                    "color:green" "color:red" "color:blue")))

(defn do-post-request
  "Sends asynchronously post request with rom-params & triggers handler on response"
  [handler url form-params]
  (go (let [response (<! (http/post url {:form-params form-params}))]
        (debug-request url form-params response)
        (handler response))))
