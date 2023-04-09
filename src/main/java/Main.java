import javax.sound.midi.*;

public class Main {
  public static void main(String[] args) throws MidiUnavailableException {
    // 获取默认的MIDI设备
    Synthesizer synth = MidiSystem.getSynthesizer();
    synth.open();

    // 获取MIDI通道
    MidiChannel[] channels = synth.getChannels();
    MidiChannel drumsChannel = channels[9]; // 架子鼓频道
    MidiChannel chordsChannel = channels[0]; // 和弦频道
    MidiChannel melodyChannel = channels[1]; // 主旋律频道

    // 架子鼓部分
    new Thread(() -> {
      try {
        // 打击乐器的音符和时长
        int[] notes = {42, 38, 46, 38, 42, 38, 46, 38, 50, 45, 50, 45, 50, 45, 50, 45};
        int[] lengths = {2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1};

        // 播放每个音符
        for (int i = 0; i < notes.length; i++) {
          drumsChannel.noteOn(notes[i], 60);
          Thread.sleep(100 * lengths[i]);
          drumsChannel.noteOff(notes[i]);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    // 和弦部分
    new Thread(() -> {
      try {
        // 和弦的音符和时长
        int[] notes = {60, 64, 67, 72, 76, 79, 84};
        int[] lengths = {4, 4, 4, 4, 4, 4, 4};

        // 播放每个音符
        for (int i = 0; i < notes.length; i++) {
          chordsChannel.noteOn(notes[i], 40);
          Thread.sleep(500 * lengths[i]);
          chordsChannel.noteOff(notes[i]);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    // 主旋律部分
    new Thread(() -> {
      try {
        // 主旋律的音符和时长
        int[] notes = {60, 60, 67, 67, 69, 69, 67, 65, 65, 64, 64, 62, 62, 60};
        int[] lengths = {4, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 2};

        // 播放每个音符
        for (int i = 0; i < notes.length; i++) {
          melodyChannel.noteOn(notes[i], 100);
          Thread.sleep(500 * lengths[i]);
          melodyChannel.noteOff(notes[i]);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}
