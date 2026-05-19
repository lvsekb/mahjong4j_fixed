package org.mahjong4j.yaku.normals;

import org.mahjong4j.hands.Shuntsu;

import java.util.List;

/**
 * @author yu1ro
 */
public abstract class PeikoResolver implements NormalYakuResolver {
    protected int peiko(List<Shuntsu> shuntsuList) {
        if (shuntsuList.size() < 2) {
            return 0;
        }

        Shuntsu stockOne = null;
        Shuntsu stockTwo = null;
        Shuntsu stockThree = null;
        // 需要注意判断一色三通顺这样的奇怪牌型
        int peiko = 0;
        for (Shuntsu shuntsu : shuntsuList) {
            //鳴いている場合はfalse
            if (shuntsu.isOpen()) {
                return 0;
            }

            if (stockOne == null) {
                stockOne = shuntsu;
                continue;
            }

            //１つ目の盃口が見つかった
            if (stockOne.equals(shuntsu)) {
                stockOne = stockTwo;
                stockTwo = stockThree;
                peiko += 1;
                continue;
            }

            if (stockTwo == null) {
                stockTwo = shuntsu;
                continue;
            }

            if (stockTwo.equals(shuntsu)) {
                stockTwo = stockThree;
                peiko += 1;
                continue;
            }

            if (stockThree == null) {
                stockThree = shuntsu;
                continue;
            }

            if (stockThree.equals(shuntsu)) {
                peiko += 1;
                stockThree = null;
            }
        }
        return peiko;
    }
}
