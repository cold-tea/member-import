package bqtest.service;

import bqtest.service.impl.MemberFileProcessorImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MemberFileProcessorTest {

    private List<Member> members;

    @Before
    public void initializeMembers() {
        members = new ArrayList<>();

        Member member = new Member();
        member.setId("100");
        member.setFirstName("John");
        member.setState("NY");
        members.add(member);

        Member member1 = new Member();
        member1.setId("101");
        member1.setFirstName("Jack");
        member1.setState("MN");
        members.add(member1);

        Member member2 = new Member();
        member2.setId("100");
        member2.setFirstName("John");
        member2.setState("NY");
        members.add(member2);
    }

    @Test
    public void getNonDuplicateMembers_forProvidedMembers_removeDuplicate() {
        MemberFileProcessor memberFileProcessor = new MemberFileProcessorImpl(null);
        List<Member> nonDuplicateMembers = memberFileProcessor.getNonDuplicateMembers(members);
        assertEquals(2, nonDuplicateMembers.size());
    }

    @Test
    public void splitMembersByState_forGivenMembers_split() {
        MemberFileProcessor memberFileProcessor = new MemberFileProcessorImpl(null);
        Map<String, List<Member>> stateSplittedMembers = memberFileProcessor.splitMembersByState(members);
        assertEquals(2, stateSplittedMembers.size());
    }

    @Test
    public void splitMembersByState_forGivenMembers_splitAndCheckEachSplits() {
        MemberFileProcessor memberFileProcessor = new MemberFileProcessorImpl(null);
        Map<String, List<Member>> stateSplittedMembers = memberFileProcessor.splitMembersByState(members);
        for(Map.Entry<String, List<Member>> splits : stateSplittedMembers.entrySet()) {
            Set stateInEachSplits = new HashSet();
            splits.getValue().forEach(split -> stateInEachSplits.add(split.getState()));
            assertEquals(1, stateInEachSplits.size());
        }
    }
}
