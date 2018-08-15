(ns cljoodle.http.moodle.requests
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn _debug-request
  [url form-params response]
  (if goog.DEBUG
    (js/console.log (str "%cRequested url: " url ",\n"
                         "Params: " form-params ",\n"
                         "%cResponse: " response)
                    "color:green" "color:blue")))

(defn do-post-request
  "Sends asynchronously post request with rom-params & triggers handler on response"
  [handler url form-params]
  (go (let [response (<! (http/post url {:form-params form-params}))]
        (_debug-request url form-params response)
        (handler response))))
