package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {

            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("lkjsfa", "XXA", 12, 34));
            players.add(new Player("oeiurowi", "XXA", 1, 88));
            players.add(new Player("qbemnrbqw",   "XZX", 44, 44));
            players.add(new Player("oidoiuva", "XZX", 13, 31));
            players.add(new Player("anebmr", "JJJ", 7, 63));
            players.add(new Player("kjehf", "JJJ", 30, 30));
            players.add(new Player("XXXXXXX", "XXA", 0, 100));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }

    @Test
    public void PlayerNotFoundIfInvalidName() {
        Player player = stats.search("AAA");
        assertNull(player);
    }

    @Test
    public void CorrectPlayerByName() {
        Player player = stats.search("anebmr");
        assertEquals(70, player.getPoints());
        assertEquals("JJJ", player.getTeam());
    }

    @Test
    public void PlayersInCorrectTeams() {
        List<Player> list = stats.team("XXA");
        assertEquals("lkjsfa", list.get(0).getName());
        assertEquals(88, list.get(1).getAssists());
        assertEquals(0, list.get(2).getGoals());
    }

    @Test
    public void GetCorrectTopScores() {
        List<Player> list = stats.topScorers(3);
        assertEquals(100, list.get(0).getPoints());
        assertEquals("oeiurowi", list.get(1).getName());
        assertEquals(44, list.get(2).getGoals());
    }
}
