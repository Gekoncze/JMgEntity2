package cz.mg.entity.storage;

import cz.mg.annotations.classes.Test;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.test.Assert;

public @Test class EncoderTest {
    public static void main(String[] args) {
        System.out.print("Running " + EncoderTest.class.getSimpleName() + " ... ");

        EncoderTest test = new EncoderTest();
        test.testIntegerEncoding();
        test.testStringEncoding();
        test.testMetadataEncoding();

        System.out.println("OK");
    }

    private void testIntegerEncoding() {
        testIntegerEncoding(Integer.MIN_VALUE);
        testIntegerEncoding(-9000000);
        testIntegerEncoding(-123);
        testIntegerEncoding(-7);
        testIntegerEncoding(-1);
        testIntegerEncoding(0);
        testIntegerEncoding(1);
        testIntegerEncoding(7);
        testIntegerEncoding(123);
        testIntegerEncoding(9000000);
        testIntegerEncoding(Integer.MAX_VALUE);
    }

    private void testIntegerEncoding(@Mandatory Integer value) {
        Encoder encoder = Encoder.getInstance();
        Integer decodedValue = encoder.decodeInteger(encoder.encodeInteger(value));
        Assert.assertEquals(value, decodedValue);
    }

    private void testStringEncoding() {
        testStringEncoding("");
        testStringEncoding(" ");
        testStringEncoding("a");
        testStringEncoding("abc");
        testStringEncoding(" abc");
        testStringEncoding("abc ");
        testStringEncoding(" abc ");
        testStringEncoding(" a b c ");
        testStringEncoding("1");
        testStringEncoding("123");
        testStringEncoding("-123");
        testStringEncoding("+123!");
        testStringEncoding(";,.?!5$#@^&{}[]{}");
        testStringEncoding("\t\n\r");
    }

    private void testStringEncoding(@Mandatory String value) {
        Encoder encoder = Encoder.getInstance();
        String decodedValue = encoder.decodeString(encoder.encodeString(value));
        Assert.assertEquals(value, decodedValue);
    }

    private void testMetadataEncoding() {
        testMetadataEncoding(new FileMetadata(FileMetadata.CURRENT_TYPE, FileMetadata.CURRENT_VERSION));
    }

    private void testMetadataEncoding(@Mandatory FileMetadata metadata) {
        Encoder encoder = Encoder.getInstance();
        FileMetadata decodedMetadata = encoder.decodeMetadata(encoder.encodeMetadata(metadata));
        Assert.assertEquals(metadata.getType(), decodedMetadata.getType());
        Assert.assertEquals(metadata.getVersion(), decodedMetadata.getVersion());
    }
}
