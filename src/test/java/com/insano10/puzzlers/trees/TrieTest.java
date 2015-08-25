package com.insano10.puzzlers.trees;

import org.junit.Test;

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
}