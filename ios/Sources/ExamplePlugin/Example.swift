import Foundation

@objc public class Example: NSObject {

    private func cacheFileURL() -> URL {
        let docs = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask).first!
        return docs.appendingPathComponent("my_cache.json")
    }

    private func readAll() -> [String: String] {
        let url = cacheFileURL()
        guard let data = try? Data(contentsOf: url),
              let dict = try? JSONSerialization.jsonObject(with: data) as? [String: String]
        else { return [:] }
        return dict
    }

    private func writeAll(_ dict: [String: String]) {
        let url = cacheFileURL()
        guard let data = try? JSONSerialization.data(withJSONObject: dict) else { return }
        try? data.write(to: url)
    }

    @objc public func echo(_ value: String) -> String {
        return value
    }

    @objc public func set(key: String, value: String) {
        var dict = readAll()
        dict[key] = value
        writeAll(dict)
    }

    @objc public func get(key: String) -> String? {
        return readAll()[key]
    }

    @objc public func remove(key: String) {
        var dict = readAll()
        dict.removeValue(forKey: key)
        writeAll(dict)
    }
}
