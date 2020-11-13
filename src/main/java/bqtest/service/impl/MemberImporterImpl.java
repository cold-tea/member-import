package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberImporter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberImporterImpl implements MemberImporter {

    private static final Integer ID_LENGTH = 12;
    private static final Integer LNAME_LENGTH = 25;
    private static final Integer FNAME_LENGTH = 25;
    private static final Integer ADDRESS_LENGTH = 30;
    private static final Integer CITY_LENGTH = 20;
    private static final Integer STATE_LENGTH = 4;
    private static final Integer ZIP_LENGTH = 5;

    public List<Member> importMembers(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath())
                .map(line -> {
                    String id = line.substring(0, ID_LENGTH);
                    String lName = line.substring(ID_LENGTH, ID_LENGTH + LNAME_LENGTH);
                    String fName = line.substring(ID_LENGTH + LNAME_LENGTH, ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH);
                    String address = line.substring(ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH,
                            ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH + ADDRESS_LENGTH);
                    String city = line.substring(ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH + ADDRESS_LENGTH,
                            CITY_LENGTH + ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH + ADDRESS_LENGTH);
                    String state = line.substring(ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH + ADDRESS_LENGTH + CITY_LENGTH,
                            ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH + ADDRESS_LENGTH + CITY_LENGTH + STATE_LENGTH);
                    String zip = line.substring(ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH + ADDRESS_LENGTH + CITY_LENGTH + STATE_LENGTH,
                            ID_LENGTH + LNAME_LENGTH + FNAME_LENGTH + ADDRESS_LENGTH + CITY_LENGTH + STATE_LENGTH + ZIP_LENGTH);
                    Member member = new Member();
                    member.setId(id.trim());
                    member.setLastName(lName.trim());
                    member.setFirstName(fName.trim());
                    member.setAddress(address.trim());
                    member.setCity(city.trim());
                    member.setState(state.trim());
                    member.setZip(zip.trim());
                    return member;
                }).collect(Collectors.toList());
    }

}
