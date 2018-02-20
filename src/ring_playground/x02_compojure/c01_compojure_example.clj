(ns ring-playground.x02_compojure.c01-compojure-example
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.adapter.jetty :refer [run-jetty]]
    [immutant.web :as immutant]
    [mount.core :as mount :refer [defstate only]]
    [clj-time.core :as time]))

(defroutes app
           (GET "/" [] "<h1>Hello World!</h1>")
           (GET "/time" [] (str "The time is: " (time/now)))
           (route/not-found "<h1>Page not found</h1>"))

(defstate server
          :start (immutant/run #'app {:port 3000})
          :stop (immutant/stop app))

(defn start []
  (mount/start (only #{#'server})))

(defn stop []
  (mount/stop))