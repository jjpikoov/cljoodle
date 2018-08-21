(ns cljoodle.http.common
  (:require [env.moodle.config :as moodle-config]))

; TODO move somwhere else
(def _rest-server-endpoint (str "/webservice/rest/server.php"
                                "?" moodle-config/moodle-json-wrap-response-url-param))

(def rest-server-url
  (str moodle-config/url-and-port
       _rest-server-endpoint))
