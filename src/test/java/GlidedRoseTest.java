import org.example.GlidedRose;
import org.example.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlidedRoseTest {

    private GlidedRose glidedRose;

    @BeforeEach
    void setUp() {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };
        glidedRose = new GlidedRose(items);
    }

    @Test
    void testRegularItemQualityDecreases() {
        glidedRose.updateQuality();
        assertEquals(19, glidedRose.items[0].quality);
        assertEquals(6, glidedRose.items[2].quality);
    }

    @Test
    void testAgedBrieQualityIncreases() {
        glidedRose.updateQuality();
        assertEquals(1, glidedRose.items[1].quality);
    }

    @Test
    void testSulfurasQualityRemainsConstant() {
        glidedRose.updateQuality();
        assertEquals(80, glidedRose.items[3].quality);
    }

    @Test
    void testBackstagePassQualityIncreases() {
        glidedRose.updateQuality();
        assertEquals(21, glidedRose.items[4].quality);
    }

    @Test
    void testBackstagePassQualityIncreasesWithDaysLeft() {
        glidedRose.items[4].sellIn = 10;
        glidedRose.updateQuality();
        assertEquals(22, glidedRose.items[4].quality);
    }

    @Test
    void testBackstagePassQualityDropsToZeroAfterConcert() {
        glidedRose.items[4].sellIn = 0;
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[4].quality);
    }

    @Test
    void testQualityCannotExceed50() {
        glidedRose.items[1].quality = 50;
        glidedRose.updateQuality();
        assertEquals(50, glidedRose.items[1].quality);
    }

    @Test
    void testQualityCannotBeNegative() {
        glidedRose.items[0].quality = 0;
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[0].quality);
    }

    @Test
    void testConjuredItemQualityDecreasesTwiceAsFast() {
        glidedRose.updateQuality();
        assertEquals(5, glidedRose.items[5].quality);
    }

    @Test
    void testConjuredItemQualityCannotBeNegative() {
        glidedRose.items[5].quality = 0;
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[5].quality);
    }

    @Test
    void testQualityUpdateAfterSellInDate() {
        glidedRose.items[0].sellIn = 0;
        glidedRose.updateQuality();
        assertEquals(18, glidedRose.items[0].quality);
    }

    @Test
    void testAgedBrieQualityIncreasesPastSellIn() {
        glidedRose.items[1].sellIn = 0;
        glidedRose.updateQuality();
        assertEquals(2, glidedRose.items[1].quality);
    }

    @Test
    void testBackstagePassesQualityIncreasesWithDaysLeft() {
        glidedRose.items[4].sellIn = 5;
        glidedRose.updateQuality();
        assertEquals(23, glidedRose.items[4].quality);
    }
    @Test
    void testQualityOfRegularItemAfterTwoDays() {

        glidedRose.updateQuality();
        glidedRose.updateQuality();
        assertEquals(18, glidedRose.items[0].quality);
    }
    @Test
    void testSulfurasQualityAfterMultipleUpdates() {
        for (int i = 0; i < 10; i++) {
            glidedRose.updateQuality();
        }
        assertEquals(80, glidedRose.items[3].quality);
    }
    @Test
    void testConjuredItemQualityDoesNotGoNegative() {
        glidedRose.items[5].quality = 1;
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[5].quality);
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[5].quality);
    }
    @Test
    void testRegularItemQualityDecreasesToZero() {
        glidedRose.items[0].quality = 1;
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[0].quality);
    }
    @Test
    void testAgedBrieQualityCapAtFifty() {
        glidedRose.items[1].quality = 49;
        glidedRose.items[1].sellIn = 0;
        glidedRose.updateQuality();
        assertEquals(50, glidedRose.items[1].quality);
    }
    @Test
    void testConjuredItemQualityDoesNotDropBelowZero() {
        glidedRose.items[5].quality = 1;
        glidedRose.updateQuality();
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[5].quality);
        glidedRose.updateQuality();
        assertEquals(0, glidedRose.items[5].quality);
    }
    @Test
    void testQualityDecreaseTwiceAsFastAfterSellInForRegularItem() {
        glidedRose.items[0].sellIn = 0;
        glidedRose.updateQuality();
        assertEquals(18, glidedRose.items[0].quality);
    }
    @Test
    void testAgedBrieQualityIncreasesFasterAfterSellIn() {
        glidedRose.items[1].sellIn = 0;
        glidedRose.updateQuality();
        assertEquals(2, glidedRose.items[1].quality);
    }



}
