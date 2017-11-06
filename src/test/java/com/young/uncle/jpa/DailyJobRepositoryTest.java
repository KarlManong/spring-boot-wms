package com.young.uncle.jpa;

import com.young.uncle.app.Application;
import com.young.uncle.entity.DailyJob;
import com.young.uncle.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DataJpaTest
public class DailyJobRepositoryTest {
    @Resource
    private DailyJobRepository dailyJobRepository;

    @Resource
    private UserRepository userRepository;

    @Test(expected = DataAccessException.class)
    public void testCreate1() {
        DailyJob dailyJob = new DailyJob();
        dailyJobRepository.save(dailyJob);
    }

    @Test
    public void testCreate2() {
        User user = new User();
        userRepository.save(user);
        DailyJob dailyJob = new DailyJob();
        dailyJob.setUser(user);
        dailyJobRepository.save(dailyJob);
    }

    @Test
    public void testQuery() {
        testCreate2();
        List<DailyJob> all = dailyJobRepository.findAll();
        assertThat(all.size(), greaterThan(0));
    }
}
