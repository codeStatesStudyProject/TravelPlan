package travelplanrepo.domain.File;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    private String fileName;

    private String storeName;

    private String fullPath;

    public File(String fileName, String storeName, String fullPath) {
        this.fileName = fileName;
        this.storeName = storeName;
        this.fullPath = fullPath;
    }
}
