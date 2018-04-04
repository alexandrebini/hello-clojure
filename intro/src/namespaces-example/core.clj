(ns namespaces.example
  (:require  [clojure.set :as s])
  (:use [clojure.java.io :only (delete-file)]))

(defn do-union [& sets]
  (apply s/union sets))

(defn delete-old-files [& files]
  (doseq [f files]
    (delete-file f)))

