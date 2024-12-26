//package com.example.sample.demo.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ChatService {
//    private final ChatRepository chatRepository;
//    private final MemberRepository memberRepository;
//
//    public List<ChatRoomList> getChatRoomList(Long memberId) {
//        Member member = memberRepository.findMemberByMemberId(memberId).get();
//
//        List<ChatMember> chatMembers = chatRepository.findChatMemberByMember(member);
//
//        List<ChatRoomList> chatRoomList = new ArrayList<>();
//        for (ChatMember chatMember : chatMembers) {
//            ChatRoomList chatRoom = new ChatRoomList();
//            chatRoom.setChatroom_id(chatMember.getChatRoom().getChatRoomId());
//            chatRoomList.add(chatRoom);
//        }
//
//        return chatRoomList;
//    }
//}
