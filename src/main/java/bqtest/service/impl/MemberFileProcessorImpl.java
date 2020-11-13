package bqtest.service.impl;

import bqtest.service.Member;
import bqtest.service.MemberFileProcessor;
import bqtest.service.MemberImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberFileProcessorImpl extends MemberFileProcessor {

    /*
     * Implement methods here
     */


    private final MemberImporter memberImporter;

    @Autowired
    public MemberFileProcessorImpl(MemberImporter memberImporter) {
        this.memberImporter = memberImporter;
    }

    @Override
    protected MemberImporter getMemberImporter() {
        return memberImporter;
    }

    @Override
    protected List<Member> getNonDuplicateMembers(List<Member> membersFromFile) {
        Comparator<Member> memberComparator = (member1, member2) -> {
            if (member1.getId().equalsIgnoreCase(member2.getId())) return 0;
            return 1;
        };
        Set<Member> memberSet = new TreeSet<>(memberComparator);
        memberSet.addAll(membersFromFile);
        return new ArrayList<>(memberSet);
    }

    @Override
    protected Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
        Map<String, List<Member>> stateRecords = new HashMap<>();
        Set<String> states = new HashSet<>();
        validMembers.forEach(validMember -> states.add(validMember.getState()));
        states.forEach(state -> {
            List<Member> membersForState =  validMembers.stream().filter(memberImport -> memberImport.getState().equals(state)).
                    collect(Collectors.toList());
            stateRecords.put(state, membersForState);
        });
        return stateRecords;
    }

}
