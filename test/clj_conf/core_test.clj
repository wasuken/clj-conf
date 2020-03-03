(ns clj-conf.core-test
  (:require [clojure.test :refer :all]
            [clj-conf.core :refer :all]))

(deftest base-test
  (testing "base-test"
    (let [conf (read-conf "./testdir/config.conf")]
      (is (= {:hoo "bar"
              :hoge {:fuga "hoo"
                     :foo "bar"}
              :piyo {:unchi "buri"}}
             conf)))))
