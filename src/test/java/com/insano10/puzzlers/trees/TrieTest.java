package com.insano10.puzzlers.trees;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrieTest
{
    private Trie trie = new Trie();

    @Test
    public void shouldValidateInsertedString() throws Exception
    {
        trie.insert("hello");

        assertThat(trie.isValidString("hello")).isTrue();
        assertThat(trie.isValidString("world")).isFalse();
    }

    @Test
    public void shouldNotValidateEmptyString() throws Exception
    {
        trie.insert("");
        assertThat(trie.isValidString("")).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowTheInsertionOfNullString() throws Exception
    {
        trie.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowTheValidationOfNullString() throws Exception
    {
        trie.isValidString(null);
    }

    @Test
    public void shouldValidateInsertedStringsThatAreSubstringsOfEachother() throws Exception
    {
        trie.insert("awesome");
        trie.insert("awe");

        assertThat(trie.isValidString("awe")).isTrue();
        assertThat(trie.isValidString("awesome")).isTrue();

        assertThat(trie.isValidString("so")).isFalse();
    }

    /*
                           <>
                        a      b
                       a b   a
                      a     a
                     a     a
                 a c d z
                a     c s
               a
     */
    @Test
    public void shouldRetrieveStringsInSortedOrder() throws Exception
    {
        trie.insert("aaaaaaa");
        trie.insert("aaa");
        trie.insert("b");
        trie.insert("aaaazc");
        trie.insert("ab");
        trie.insert("aaaac");
        trie.insert("baaa");
        trie.insert("a");
        trie.insert("aaaad");
        trie.insert("aaaazs");

        List<String> sortedKeys = trie.getSortedKeys();

        assertThat(sortedKeys.get(0)).isEqualTo("a");
        assertThat(sortedKeys.get(1)).isEqualTo("aaa");
        assertThat(sortedKeys.get(2)).isEqualTo("aaaaaaa");
        assertThat(sortedKeys.get(3)).isEqualTo("aaaac");
        assertThat(sortedKeys.get(4)).isEqualTo("aaaad");
        assertThat(sortedKeys.get(5)).isEqualTo("aaaazc");
        assertThat(sortedKeys.get(6)).isEqualTo("aaaazs");
        assertThat(sortedKeys.get(7)).isEqualTo("ab");
        assertThat(sortedKeys.get(8)).isEqualTo("b");
        assertThat(sortedKeys.get(9)).isEqualTo("baaa");

    }

    @Test
    public void shouldSplitStringIntoValidWords() throws Exception
    {
        trie.insert("pea");
        trie.insert("nut");
        trie.insert("but");
        trie.insert("butter");
        trie.insert("utter");
        trie.insert("peanut");

        List<String> words = trie.splitIntoValidSubStrings("peanutbutter");

        assertThat(words.get(0)).isEqualTo("peanut");
        assertThat(words.get(1)).isEqualTo("butter");
    }
}