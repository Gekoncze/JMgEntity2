package cz.mg.entity.storage;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;

public @Utility class FileMetadata {
    public static final String CURRENT_TYPE = "MgEc"; // magical label to recognize file type
    public static final Integer CURRENT_VERSION = 1; // version of the file type

    public static final int TYPE_SIZE = 4; // 4 reserved bytes for type
    public static final int VERSION_SIZE = 4; // 4 reserved bytes for version
    public static final int SIZE = TYPE_SIZE + VERSION_SIZE; // must sum to 8

    static {
        if (Integer.BYTES != 4) {
            throw new IllegalStateException("Unsupported platform. Expected 4 byte integer, but got " + Integer.BYTES + " byte integer.");
        }
    }

    private @Mandatory String type;
    private @Mandatory Integer version;

    public FileMetadata(@Mandatory String type, @Mandatory Integer version) {
        this.type = type;
        this.version = version;
    }

    public @Mandatory String getType() {
        return type;
    }

    public void setType(@Mandatory String type) {
        this.type = type;
    }

    public @Mandatory Integer getVersion() {
        return version;
    }

    public void setVersion(@Mandatory Integer version) {
        this.version = version;
    }
}
