(ns tic-tac-toe.board-test
  (:require [tic-tac-toe.map :refer :all]
            [clojure.test :refer :all]))

(deftest board
  (testing "convert field to coordinate"
    (is (= (field->coordinate {:x1y2 "_"}) {:x 1 :y 2 :value "_"})))
  
  (testing "convert coordinate to board"
    (is (= (coordinate->field {:x 1 :y 2 :value "_"}) {:x1y2 "_"}))))