package cz.mg.entity.storage;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;
import cz.mg.entity.mapper.Element;
import cz.mg.test.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;

public @Test class StorageTest {
    public static void main(String[] args) {
        System.out.print("Running " + StorageTest.class.getSimpleName() + " ... ");

        StorageTest test = new StorageTest();
        test.testStorage();

        System.out.println("OK");
    }

    private void testStorage() {
        List<Element> elementsBefore = new List<>(
            new Element("foo", "foo value", new List<>()),
            new Element("bar", "", new List<>(9, 7, 2)),
            new Element("", "", new List<>()),
            new Element("fooBar", "foo value", new List<>())
        );

        ElementWriter writer = ElementWriter.getInstance();
        ElementReader reader = ElementReader.getInstance();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writer.write(elementsBefore, outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        List<Element> elementsAfter = reader.read(inputStream);

        compare(elementsBefore, elementsAfter);
    }

    private void compare(@Mandatory List<Element> elementsBefore, @Mandatory List<Element> elementsAfter) {
        Assert.assertNotSame(elementsBefore, elementsAfter);
        Assert.assertEquals(elementsBefore.count(), elementsAfter.count());
        Iterator<Element> elementsBeforeIterator = elementsBefore.iterator();
        Iterator<Element> elementsAfterIterator = elementsAfter.iterator();
        while (elementsBeforeIterator.hasNext() && elementsAfterIterator.hasNext()) {
            Element before = elementsBeforeIterator.next();
            Element after = elementsAfterIterator.next();
            compare(before, after);
        }
    }

    private void compare(@Mandatory Element before, @Mandatory Element after) {
        Assert.assertNotSame(before, after);
        Assert.assertEquals(before.getName(), after.getName());
        Assert.assertEquals(before.getValue(), after.getValue());
        Assert.assertEquals(before.getFields().count(), after.getFields().count());
        Iterator<Integer> beforeFieldIterator = before.getFields().iterator();
        Iterator<Integer> afterFieldIterator = after.getFields().iterator();
        while (beforeFieldIterator.hasNext() && afterFieldIterator.hasNext()) {
            Integer beforeField = beforeFieldIterator.next();
            Integer afterField = afterFieldIterator.next();
            Assert.assertEquals(beforeField, afterField);
        }
    }
}
