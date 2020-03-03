(ns clj-conf.core
  (:require [clojure.string :refer [split join trim]])
  (:gen-class))

(defn read-conf [path]
  (let [lines (map trim (split (slurp path) #"\n"))
        nspace (atom "")
        result (atom {})]
    (doseq [line lines]
      (cond (and (= \[ (first line)) (= \] (first (reverse line))))
            (reset! nspace (join (reverse (drop 1 (reverse (drop 1 line))))))
            (some #(= \= %) line)
            (let [parse (split line #"=")]
              (reset! result (merge-with merge @result
                                         (if (= @nspace "")
                                           {(keyword (trim (first parse)))
                                            (trim (join "=" (drop 1 parse)))}
                                           {(keyword @nspace)
                                            {(keyword (trim (first parse)))
                                             (trim (join "=" (drop 1 parse)))}}))))))
    @result))
