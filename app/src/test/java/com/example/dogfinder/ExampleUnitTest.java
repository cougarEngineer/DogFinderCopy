package com.example.dogfinder;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void gettingListTest() {
        DogProfileDatabaseInteractor dog = new DogProfileDatabaseInteractor();
        List<DogProfile> list = dog.get();
        assertNotNull(list);
    }

    @Test
    public void dogProfileColorTest() {
        DogProfile profile = new DogProfile();
        assertEquals("Default Color isn't Unknown", Color.Unknown, profile.getColor());
    }

    @Test
    public void dogProfileBreedTest() {
        DogProfile breed = new DogProfile();
        assertEquals(Breed.Unknown, breed.getBreed());
    }
}