package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.meetingroom.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-09-20
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxCpOaMeetingRoomServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * Test add.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testAdd() throws WxErrorException {
    this.wxService.getOaMeetingRoomService().addMeetingRoom(WxCpOaMeetingRoom.builder()
      .building("腾讯大厦")
      .capacity(10)
      .city("深圳")
      .name("18F-会议室")
      .floor("18F")
      .equipment(Arrays.asList(1, 2))
//        .coordinate()
      .build());

  }

  /**
   * Test update.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testUpdate() throws WxErrorException {
    this.wxService.getOaMeetingRoomService().editMeetingRoom(WxCpOaMeetingRoom.builder()
      .building("腾讯大厦")
      .capacity(10)
      .city("深圳")
      .name("16F-会议室")
      .floor("16F")
      .equipment(Arrays.asList(1, 2, 3))
      .meetingroomId(1)
      .build());
  }

  /**
   * Test get.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGet() throws WxErrorException {
    final List<WxCpOaMeetingRoom> meetingRooms =
      this.wxService.getOaMeetingRoomService().listMeetingRoom(WxCpOaMeetingRoom.builder()
        .building("腾讯大厦")
        .city("深圳")
        .equipment(Arrays.asList(1, 2))
        .build());
    assertThat(meetingRooms).isNotEmpty();
  }

  /**
   * Test delete.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testDelete() throws WxErrorException {
    Integer calId = 1;
    this.wxService.getOaMeetingRoomService().deleteMeetingRoom(calId);
  }

  @Test
  public void testGetMeetingRoomBookingInfo() throws WxErrorException {
    final WxCpOaMeetingRoomBookingInfoResult meetingRoomBookingInfo = this.wxService.getOaMeetingRoomService().getMeetingRoomBookingInfo(WxCpOaMeetingRoomBookingInfoRequest.builder()
      .meetingroomId(19)
      .build());
    System.out.println(meetingRoomBookingInfo);
    assertThat(meetingRoomBookingInfo).isNotNull();
  }

  @Test
  public void testBookingMeetingRoom() throws WxErrorException {
    WxCpOaMeetingRoomBookResult wxCpOaMeetingRoomBookResult = this.wxService.getOaMeetingRoomService().bookingMeetingRoom(WxCpOaMeetingRoomBookRequest.builder().subject("测试会议").meetingroomId(19).startTime(1730118646).endTime(1730120446).booker("LiangLinWei").attendees(Arrays.asList("LiangLinWei", "ZhaoYuCheng")).build());
    System.out.println(wxCpOaMeetingRoomBookResult);
    assertThat(wxCpOaMeetingRoomBookResult).isNotNull();
  }

  @Test
  public void testBookingMeetingRoomBySchedule() throws WxErrorException {
    WxCpOaMeetingRoomBookResult wxCpOaMeetingRoomBookResult = this.wxService.getOaMeetingRoomService().bookingMeetingRoomBySchedule(WxCpOaMeetingRoomBookByScheduleRequest.builder()
      .booker("LiangLinWei")
      .meetingroomId(19)
      .schedule_id("bkWChLPrv9YpPRLeeYU-uFwl9BQX3G2_rQYQRg1W1uR3A")
      .build());
    System.out.println(wxCpOaMeetingRoomBookResult);
    assertThat(wxCpOaMeetingRoomBookResult).isNotNull();
  }

  @Test
  public void testBookingMeetingRoomByMeeting() throws WxErrorException {
    WxCpOaMeetingRoomBookResult wxCpOaMeetingRoomBookResult = this.wxService.getOaMeetingRoomService().bookingMeetingRoomByMeeting(WxCpOaMeetingRoomBookByMeetingRequest.builder()
      .booker("LiangLinWei")
      .meetingroomId(19)
      .meetingid("bkWChLPrv9YpPRLeeYU-uFwl9BQX3G2_rQYQRg1W1uR3A")
      .build());
    System.out.println(wxCpOaMeetingRoomBookResult);
    assertThat(wxCpOaMeetingRoomBookResult).isNotNull();
  }

  @Test
  public void testCancelBookMeetingRoom() throws WxErrorException {
    this.wxService.getOaMeetingRoomService().cancelBookMeetingRoom(WxCpOaMeetingRoomCancelBookRequest.builder().booking_id("bkWChLPrv9YpPRLeeYU-uFwl9BQX3G2_rQYQRg1W1uR3A").build());
  }

  @Test
  public void testGetBookingInfoByBookingId() throws WxErrorException {
    WxCpOaMeetingRoomBookingInfoByBookingIdResult bookingInfoByBookingId = this.wxService.getOaMeetingRoomService().getBookingInfoByBookingId(WxCpOaMeetingRoomBookingInfoByBookingIdRequest.builder().meetingroom_id(19).booking_id("bkWChLPrv9YpPRLeeYU-uFwl9BQX3G2_rQYQRg1W1uR3A").build());
    System.out.println(bookingInfoByBookingId);
    assertThat(bookingInfoByBookingId).isNotNull();
  }


}
