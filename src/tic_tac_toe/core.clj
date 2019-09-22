(ns tic-tac-toe.core
  (:gen-class)
  (:require [tic-tac-toe.logic :refer :all]
            [tic-tac-toe.board :refer :all]
            [clojure.string :as s]))

(defn read-coordinate [line]
  (vec (map read-string (s/split line #" "))))

(defn game-loop [board player]
  (loop [board board
         player player]
    (println "Player" player "play [x y]:")
    (def new-board (set-on-board board player (read-coordinate (read-line))))
    (show-board new-board)
    (if (winner? new-board)
      (str (get-winner new-board) "ganhou!")
      (do 
        (if (draw? new-board)
          "Empate"
          (recur new-board (next-player new-board)))))))

(defn -main [& args]
  (println "Bem vindo ao jogo da velha")
  (println (game-loop (create-board) "X")))